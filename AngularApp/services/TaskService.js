angular.module('todoApp', [])
.factory('TaskService', ['$http', function($http) {
    return {
        getAllTasks: function() {
            return $http.get("http://localhost:8080/api/v1/tasks");
        },
        getUserByUserId: function(userId) {
            return $http.get("http://localhost:8080/api/v1/users/" + userId);
        },
        createTask: function(newTask) {
            return $http.post("http://localhost:8080/api/v1/tasks", newTask)
        },
        deleteTaskByTaskId: function(userId, taskId) {
            return $http.delete("http://localhost:8080/api/v1/users/" + userId + "/tasks/" + i);
        }
    };
}]);