var chat = (function Chat() {
    "use strict";

    var socket = null;
    var handler;

    var connect = (function(host) {
        if ('WebSocket' in window) {
            socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            socket = new MozWebSocket(host);
        } else {
            console.log('Error: Websocket is not supported by this browser.');
            return;
        }

        socket.onopen = function() {
            console.log('Info: Websocket connection opened.');
            if (handler) {
                handler.onopen();
            }
        }

        socket.onclose = function() {
            console.log('Info: Websocket closed.');
            if (handler) {
                handler.onclose();
            }
        }

        socket.onmessage = function(message) {
            console.log('response: ' + message.data);
            if (handler) {
                handler.onmessage(message.data);
            }
        }
    });

    var initialize = function(callback) {
        handler = callback;
        var ep = './websocket/chat';
        if (window.location.protocol == 'http:') {
            connect('ws://' + window.location.host + ep);
        } else {
            connect('wss://' + window.location.host + ep);
        }
    }
    
    var sendMessage = (function(message) {
        if (socket) {
            socket.send(JSON.stringify(message));
        }
    });

    return {
        initialize: initialize,
        sendMessage: sendMessage
    };
});
