app.controller("staff-ctrl", function ($scope, $http) {

    $scope.items = [];
    $scope.roles = [];
    $scope.form = {};

    // tải thông tin từ server về
    $scope.initialize = function () {
        // load product
        $http.get("/rest/accounts/list").then(resp => {
            $scope.items = resp.data;
        });
        // đổ dữ liệu vào trong vai trò
        // load vai trò
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        });
        $scope.reset();
    }

    //reset form
    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true,
        };
    }

    //thêm mới sản phẩm
    $scope.create = function () {
        // tạo mới một sản phẩm lấy thông tin trên form
        var item = angular.copy($scope.form);
        $http.post(`/rest/products`, item).then(resp => {
            // chuyển ngày sang js
            resp.data.createDate = new Date(resp.data.createDate)
            // bỏ vào trong list
            $scope.items.push(resp.data);
            // reset lại form
            $scope.reset();
            alert("Thêm mới sản phẩm thành công!");
        }).catch(error => {
            alert("Thêm mới sản phẩm lỗi!");
            console.log("Error", error);
        });
    }

    //cập nhật sản phẩm
    $scope.update = function () {
        // lấy dữ liệu trong form
        var item = angular.copy($scope.form);
        // đưa dữ liệu lên server
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            // dữ liệu phải hồi lại tìm trong product theo id giống id server phản hồi lại
            var index = $scope.items.findIndex(p => p.id == item.id);
            // thay đổi lại sản phẩm trong list ở client
            $scope.items[index] = item;
            alert("Cập nhật sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm!");
            console.log("Error", error);
        })
    }

    // xóa sản phẩm
    $scope.delete = function (item) {
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            // tìm thấy và xóa, xóa 1 phần tử trong mảng js dùng splice
            $scope.items.splice(index, 1);
            // sao khi xóa reset lại form
            $scope.reset();
            alert("Xóa sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi xóa sản phẩm!");
            console.log("Error", error);
        })
    }

    // hiển thị lên form
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
    }

    //upload hình ảnh
    $scope.imageChanged = function(files){
        //tao ra 1 đối tượng
        var data = new FormData();
        // lấy ra file ảnh mà người dùng chọn
        data.append('file',files[0]);
        // post file lên server
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            // upload thành công, chỉ lây ra name để hiện anh lên giao diện
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi upload hình ảnh!");
            console.log("Error",error);
        })
    }

    $scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		
		get count() {
			return Math.ceil(1.0 * $scope.items.length /this.size);
		},
		
		first(){
			this.page=0;
		},
		prev(){
			this.page --;
			if(this.page<0){
				this.last();
			}
		},
		next() {
			this.page ++;
			if(this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

    // khởi đầu
    $scope.initialize();

}) 