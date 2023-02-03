app.controller("authority-ctrl", function($scope, $http) {
    
    // load all roles
    $http.get("/rest/roles").then(resp => {
        $scope.roles = resp.data;
    })

    // load staff and directors (administrators)
    $http.get("/rest/accounts?admin=true").then(resp => {
        $scope.admins = resp.data;
    })

    // load authorites of staffs and directors
    $http.get("/rest/authorities?admin=true").then(resp => {
        $scope.authorities = resp.data;
    }).catch(erro => {
        $location.path("/unauthprized");
    })

})