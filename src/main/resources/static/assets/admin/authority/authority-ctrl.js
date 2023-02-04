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
    }).catch(error => {
        $location.path("/unauthorized");
    })

    // tìm xem với acc, role có tk nào trùng vs cái người dùng check hây k
    $scope.authority_of = function (acc, role){
        if($scope.authorities) {
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function(acc, role) {
        var authority = $scope.authority_of(acc, role);
        if(authority) { // đã cấp quyền => thu hồi quyền (xóa)
            $scope.revoke_authority(authority);
        } else { // chưa được cấp quyền => cấp quyên ( thêm mới )
            authority = {account: acc, role: role};
            $scope.grant_authority(authority);
        }
    }

    // xóa authority

})