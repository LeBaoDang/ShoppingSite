const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {

    $scope.cart = {

        items: [],
        // them san pham vao gio hang
        // dựa vào id để đi kiểm tra trong giỏ hàng
        add(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                // nếu đã tồn tại thì tăng sl lên
                item.qty++;
                // lưu vào local
                this.saveToLocalStorage();
            } else {
                // chưa có thì đi tải sp trên server về
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },

        // xóa sản phẩm khỏi giỏ hàng
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            // pt splice xóa 1 phần tử khởi mảng
            this.items.splice(index, 1);
            // xóa xong
            this.saveToLocalStorage();
        },

        // xóa trắng mặt hàng dc lưu trong giỏ
        clear() {
            this.items = []
            this.saveToLocalStorage();
        },

        // tính tổng số lượng các mặt hàng trong giỏ
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        // tổng thành tiền các mặt hàng trong giỏ
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },

        // lưu giỏ hàng vào local storage
        saveToLocalStorage() {
            // chuyển mặt hàng sang json 
            var json = JSON.stringify(angular.copy(this.items));
            // sao đó lưu vào loca vs tên là cart
            localStorage.setItem("cart", json);
        },

        /* đọc giỏ hàng từ local storage vào bộ nhớ để load trang vẫn có cái hiển thị
           khi ud bắt đầu tải lại các mặt hàng đã lưu trong local storage vào trong cart */
        loadFromLocalStorage() {
            // đọc lại cart từ trong local
            var json = localStorage.getItem("cart");
            // nếu có chuyển sang json và gán vào item ko thì gán [] = null
            this.items = json ? JSON.parse(json) : [];
        },
    }

    $scope.cart.loadFromLocalStorage();

    
    // định nghĩa 1 đối tượng order
    $scope.order = {
        
        // ngày hiện tại
        createDate: new Date(),
        address: "",
        // lấy username
        account: {username: $("#username").text()}, 
        // lấy toàn bộ mặt hàng bên trong giỏ hàng gửi lên server
        get orderDetails(){
            return $scope.cart.items.map(item => {
                return {
                    // duyệt qua các mặt hàng có trong giỏ hàng
                    // mỗi mặt hàng tạo ra 1 order detail
                    product:{id: item.id},
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        
        // nút đặt hàng && gửi thong tin lên server
        purchase(){

           var order = angular.copy(this);
           // thực hiện đặt hàng
           $http.post("/rest/orders", order).then(resp => {
            alert("Đặt hàng thành công!");
            //đặt hàng xong xóa giỏ hàng
            $scope.cart.clear();
            // chuyển sang trang chi tiết đơn hàng
            location.href = "/order/detail/" + resp.data.id;
           }).catch(error => {
            alert("Đặt hàng lỗi!")
            console.log(error)
           })
           
        }
        
    }
    
})