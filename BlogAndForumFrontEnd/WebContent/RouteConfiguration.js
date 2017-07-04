/**
 *  Routing provided to all the HTML pages using the $routeProvider
 */

myApp.config(function($routeProvider){
	
	$routeProvider
	
	.when('/blog_list', {
		templateUrl : 'baf_blog/blog_list.html'
	})
	
	.when('/user_login', {
		templateUrl : 'baf_users/user_login.html'
	});
});