app.controller("product-ctrl", function ($scope, $http) {

    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    // tải thông tin từ server về
    $scope.initialize = function () {
        // load product
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            // chuyển đỏi ngày tháng về js vì khi tải về nó là chuỗi
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });
        // đổ dữ liệu vào trong category
        // load category
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });
    }

    //upload hình ảnh
    // $scope.imageChanged = function(files){
    //     //tao ra 1 đối tượng
    //     var data = new FormData();
    //     // lấy ra file ảnh mà người dùng chọn
    //     data.append('file',files[0]);
    //     // post file lên server
    //     $http.post('/rest/upload/images', data, {
    //         transformRequest: angular.identity,
    //         headers: {'Content-Type': undefined}
    //     }).then(resp => {
    //         // upload thành công, chỉ lây ra name để hiện anh lên giao diện
    //         $scope.form.image = resp.data.name;
    //     }).catch(error => {
    //         alert("Lỗi upload hình ảnh");
    //         console.log("Error",error);
    //     })
    // }

    // upload ảnh
    // $scope.imageChanged = function (files) {
    //     var data = new FormData();
    //     data.append('file', files[0]);
    //     $http.post('/rest/upload/images', data, {
    //         transformRequest: angular.identity,
    //         headers: {'Content-Type': undefined }
    //     }).then(resp => {
    //         $scope.form.image = resp.data.name;
    //     }).catch(error => {
    //         alert("lỗi up hình");
    //         consolee.log("Error", error);
    //     })
    // }
    $scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/img/images', data, {
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Upload lỗi không thành công !")
			console.log("Error", error)
		})
	}

    //reset form
    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true,
        };
    }

    // thêm mới sản phẩm
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/products`, item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("thêm mới sản phẩm thành công");
        }).catch(error => {
            alert("thêm mới sản phẩm lỗi");
            console.log("Error", error);
        });
    }

    //cập nhật sản phẩm
    $scope.update = function () {
        alert("update");
    }

    // xóa sản phẩm
    $scope.delete = function () {
        alert("delete");
    }

    // hiển thị lên form
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
    }

    // khởi đầu
    $scope.initialize();

}) 
