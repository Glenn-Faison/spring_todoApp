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
}])
.controller('TaskController', ['TaskService', function(TaskService) {
    var self = this;
    self.taskList = [];
    self.selectedIndices = [];
    self.newTask = {
        completed: false,
        description: "",
        owner: {
            email: "",
            password: "",
            username: ""
        }
    };
    
    self.getTasks = function() {
        TaskService.getAllTasks()
            .then(function(result) {
                self.taskListPage = result.data;
                self.taskList = self.taskListPage.content;
            });
    };

    self.getStyle = function(task) {
        if (task == null) {
            return 'isEven';
        }
        var index = self.taskList.indexOf(task);
        if (index % 2 == 0) {
            return 'isEven';
        }
        return 'isOdd';
    };

    self.addTask = function() {
        TaskService.getUserByUserId(self.newTask.owner.userId)
            .then(function(result) {
                self.newTask.owner = result.data;
                TaskService.createTask(self.newTask)
                    .then(self.getTasks);
            })
            .then(function() {
                self.newTask = {};
            });
    };

    self.selectTask = function(index) {
        self.selectedIndices[index] = !self.selectedIndices[index];
    };

    self.deleteTasks = function() {
        for (i in self.selectedIndices) {
            TaskService.deleteTaskByTaskId(1, i)
                .then(self.getTasks);
        }
    };

    self.getTasks();
}]);