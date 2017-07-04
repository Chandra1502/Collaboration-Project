/**
 *  Angular controller for Blog Module
 */

myApp.controller('BlogController',

		function($scope,$http)
		{
			$http.get("http://localhost:8080/BlogAndForum/getListOfBlog")
			.then(function(response){
				$scope.bloglist = response.data;
			});
		}

);