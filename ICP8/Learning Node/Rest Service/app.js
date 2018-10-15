
var app = angular.module('youTubeApp', []);
app.controller('youTubeCtrl', function ($scope, $http) {
    //Declaring the variables
    $scope.searchTerm = "";
    $scope.showError = false;
    $scope.results = [];
    $scope.isVideo = false;
    /*
    * This method will call when the user clicks on search button after entering the search key word.
    * In this method we will call the Youtube API and get the results and assign the result to $scope.results variable.
    */
    $scope.search = function () {
        console.log($scope.searchTerm);
        if ($scope.searchTerm !== "") {
            $scope.showError = false;
            $scope.isVideo = false;
            $http({
                method: 'GET',
                url: 'http://localhost:3000/getPlace',
                params: {
                    'q': $scope.searchTerm,
                }
            }).then(function successCallback(response) {
                console.log(response.data.items);
                $scope.results = response.data.items;
            }, function errorCallback(response) {
                console.log(response);
            });
        }else {
            $scope.showError = true;
            $scope.results = [];
        }
    }

    /*
    * This method will call when the user clicks on thumbnail of a video.
    * In this method we are modifying the source of iframe which is hidden and make it visible by changing the $scope.isVideo to true.
    */
    $scope.play = function (videoId) {
        $scope.isVideo = true;
        var d = document.getElementById("youtubePlay");
        d.src = "https://www.youtube.com/embed/" + videoId + "?enablejsapi=1"
    }
});