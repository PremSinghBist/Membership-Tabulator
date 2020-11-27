angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {

            fetchData();

            function fetchData() {
                $http({method: 'GET', url: 'api/load-data'}).then(function (response) {
                    console.log(response.data);
                    $scope.data = response.data;

                }, function (reason) {
                    console.log('error ' + reason)
                });
            }
            $scope.getDetails = function(){
            	var memberList = JSON.parse($scope.selectedState);
            	$scope.memberList = memberList;
            }
           

        }]);
