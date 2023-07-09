def lintchecks(){
    sh "echo Installing Jslint for ${Component}"
    sh "npm i jslint"
    sh "node_modules/jslint/bin/jslint.js server.js || true"
}

def sonarchecks(){
    sh "echo Sonarchecks inprogress"
    sh "sonar-scanner -Dsonar.sources=. -Dsonar.login=eacdbac316054f3dfbf3f2aefd07060ba5018aa5 -Dsonar.host.url=http://172.31.84.15:9000 -Dsonar.projectKey=${COMPONENT}"
}


def call(){
    pipeline {
    agent { label 'ws' }
    stages {

        stage('Lint Checks') {
            steps {
                   script {
                       lintChecks()
                  }
            }         /////
        }
        
        stage ('Code Compile') {
            steps {
                sh "npm install"
            }
        }

        stage ('Sonar Checks') {
            steps{
                script {
                    sonarChecks()
                }
            }
        }

        stage ('Testing') {
            steps{
                sh "echo Testing in-progress"
                }
            }
        }
    }
}

