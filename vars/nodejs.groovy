              sh "echo Installing Jslint"
              sh "npm i jslint"
              sh "node_modules/jslint/bin/jslint.js server.js || true"
def info(message) {
    echo "Hai, I am function who name is info"
    eho"I am printing the value of meassage is ${message}"
}

//calling the function info
info("Hello")