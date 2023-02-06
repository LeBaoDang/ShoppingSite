app = angular.module("admin-app",["ngRoute"])

app.config(function ($routeProvider){
    $routeProvider
    .when("/product", {
        templateUrl: "/assets/admin/product/index.html" ,
        controller: "product-ctrl" , 
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
	$scope.role = []
	$scope.initialize = function(){
		$http.get( "/rest/accounts/logged-in" ).then( resp => {
			$scope.loggedInStaff = resp.data
		} )
		$http.get( `/rest/accounts/logged-inn` ).then( resp => {
			$scope.role = resp.data
		})
	}
	$scope.initialize()
} )