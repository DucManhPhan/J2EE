essageTypes.GETUSERS     = 4;
MessageTypes.GETMESSAGES = 5;
MessageTypes.MESSAGELIST = 6;

function ChatViewModel(chatClient) {

    function JoinMessage(name) {
        this.type = MessageTypes.JOIN;
        this.name = name;
    }

    function NewChatMessage(userName, message) {
        this.type = MessageTypes.MESSAGE;
        this.userName = userName;
        this.message = message;
        this.timeSent = new Date();
    }

    function DisplayChatMessage(userName, message, timeSent, isFirst, isDate) {
        var self = this;
        this.type = MessageTypes.MESSAGE;
        this.userName = userName;
        this.isFirst = isFirst;
        this.isDate = isDate;
        this.timeSent = timeSent;

        this.timeSentTimeDisplay = ko.computed(function() {
            return moment(self.timeSent).format('h:mm:ss a');
        });

        this.timeSentDateDisplay = ko.computed(function() {
            return moment(self.timeSent).format('DD/MM/YYYY');
        });

        this.timeSentFullDisplay = ko.computed(function() {
            return moment(self.timeSent).format('DD/MM/YYYY, h:mm:ss a');
        });
    }

    function GetSignedOnUsersMessage() {
        this.type = MessageTypes.GETUSERS;
    }

    function User(name, me) {
        this.name = name;
        this.me = me;
    }

    var self = this;
    var SESSION_NAME = 'userName';

    var initialize = function() {
        chatClient.initialize(self.handler);
        if (sessionStorage[SESSION_NAME] && sessionStorage[SESSION_NAME].length > 0) {
            self.userName(sessionStorage[SESSION_NAME]);
            self.joined(true);
        }
    }

}