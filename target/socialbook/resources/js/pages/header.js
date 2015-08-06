angular.module('BillingApp').controller("LocationController",["$location",function($location) {
    if($location.$$absUrl.lastIndexOf('/contacts') > 0){
        this.activeURL = 'contacts';
    } else{
        this.activeURL = 'home';
    }
}])
 
.controller("UserHeaderController", ['AuthUser',function(AuthUser) {
	 this.name = AuthUser.name;
}]); 
 
 