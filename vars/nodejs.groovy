def lintchecks(){
    sh ***
              echo Installing Jslint
              npm i jslint
              node_modules/jslint/bin/jslint.js server.js || true
    ***
}

def sonarchecks(){
    sh ***
              echo sonarchecks inprogress
              npm i jslint
              sonar-scanner -Dsonar.sources=. -Dsonar.login=eacdbac316054f3dfbf3f2aefd07060ba5018aa5 -Dsonar.host.url=http://172.31.84.15:9000 -Dsonar.projectKey=${COMPONENT}

              node_modules/jslint/bin/jslint.js server.js || true
    ***
}
def call(){
    pipeline {
    agent { label 'WS' }
    stages {           ///
        stage('Lint Checks') {
            steps {
                   script {
                       nodejs.lintchecks()
                  }
            }         /////
        }
        
        stage ('Code Compile') {
            steps{
                sh "npm install"
            }
        }

        stage ('sonar checks') {
            steps{
                script {
                    sonarChecks()
                }
            }
        }
    }
}
}
