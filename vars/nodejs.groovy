def lintchecks(){
    sh "echo Installing Jslint for ${Component}"
    sh "npm i jslint"
    sh "node_modules/jslint/bin/jslint.js server.js || true"
}

def sonarchecks(){
    sh "echo Sonarchecks inprogress"
    sh "sonar-scanner -Dsonar.sources=. -Dsonar.login=eacdbac316054f3dfbf3f2aefd07060ba5018aa5 -Dsonar.host.url=http://172.31.84.15:9000 -Dsonar.projectKey=${COMPONENT} "
    sh "curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > sonar-quality-gate.sh "
    sh "sonar-quality-gate.sh ${SONARCRED_USR} ${SONARCRED_PSW} ${SONARURL} ${COMPONENT} "
}


def call(COMPONENT){
    pipeline {
        agent { label 'WS' }
        environment {
            SONARCRED = credentials('SONARCRED')
            SONARURL = "172.31.84.15"
        }
        stages {

             stage('Lint Checks') {
                 steps {
                     script {
                          lintChecks()
                  }
            }         /////
        }
        
             stage('Code Compile') {
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

             stages ('Test Cases') {
                 parallel {
                    stage('Unit testing') {
                        steps{
                           sh "echo Unit Testing started"
                           sh "echo Unit testing completed"
                        }
                    }
                    stage('Integration testing') {
                        steps{
                           sh "echo Integration Testing started"
                           sh "echo Integration testing completed"
                        }
                    }
                    stage('Functional testing') {
                        steps{
                           sh "echo Functional Testing started"
                           sh "echo Functional testing completed"
                        }
                    }
                    stage('Prepare Artifacts') {
                        steps{
                           sh "echo preparing artifacts"
                        }
                    }
                    stage('Upload Artifacts') {
                        steps{
                           sh "echo Uploading artifacts"
                        }
                    }

        }
    }
}
        }
    }