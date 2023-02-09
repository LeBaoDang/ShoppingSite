app = angular.module("admin-app",["ngRoute"])

app.config(function ($routeProvider){
    $routeProvider
    .when("/product", {
        templateUrl: "/assets/admin/product/index.html" ,
        controller: "product-ctrl" , 
    })
    .when("/staff", {
        templateUrl: "/assets/admin/staff/index.html" ,
        controller: "staff-ctrl" , 
    })
    .when("/authorize", {
        templateUrl: "/assets/admin/authority/index.html" ,
        controller: "authority-ctrl" ,
    })
    .when("/unauthorized", {
        templateUrl: "/assets/admin/authority/unauthorized.html" ,
        controller: "authority-ctrl"
    })
    .otherwise ({
        templateUrl: "/assets/admin/admin.html"
    })
})

app.controller( "admin-ctrl" , function( $scope , $http ){
	$scope.loggedInStaff = {} 
	$scope.roles = []
	$scope.initialize = function(){
		$http.get( "/rest/accounts/login-in-account" ).then( resp => {
			$scope.loggedInStaff = resp.data
		} )
		$http.get( "/rest/accounts/login-in-account-role" ).then( resp => {
			$scope.roles = resp.data
		})
	}
	$scope.initialize()
} )