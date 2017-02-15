var stompClient = null;

function ChatController($scope) {
	
	$scope.messages = [];
	$scope.roster = [];
	$scope.name = '';
	$scope.text = '';
	

	var socket = new SockJS('/plaude');
	
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);

		stompClient.subscribe('/topic/roster', function(rosterMessage) {
			showRoster(JSON.parse(rosterMessage.body).names);
		});

		stompClient.subscribe('/topic/chat', function(chatMessage) {
			addChatMessage(JSON.parse(chatMessage.body));
		});
		
		identify("Anonymous");
	});

	

	function addChatMessage(msg) {
		$scope.messages.push(msg);
		$scope.$apply();
	}

	function showRoster(names) {
		console.log('showRoster:', names);
		$scope.roster = names;
		$scope.$apply();
	}

	$scope.send = function send() {
		console.log('Sending message:', $scope.text);
		stompClient.send("/app/chat", {}, JSON.stringify({
			'name' : $scope.name,
			'text' : $scope.text
		}));
		$scope.text = '';
	};

	$scope.setName = function setName() {
		identify($scope.name);
	};
	
	function identify(name) {
		stompClient.send("/app/identify", {}, JSON.stringify({
			'name' : name
		}));
	}
}
