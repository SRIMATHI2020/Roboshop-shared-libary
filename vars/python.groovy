def lintchecks(){
    sh "echo performing Pylint for ${COMPONENT}"
    sh "pip3 install Pylint "
    sh "pylint *.py "
    sh "echo lint checks completed for ${COMPONENT} "
}
def call(){
    pipeline {
    agent { label 'WS' }
    stages {           ///
        stage('Lint Checks') {
            steps {
                   script {
                       lintchecks()
                  }
                } 
            }        /////
        }
    }
        stage ('Sonar Checks') {
                  steps{
                     script {
                          common.sonarChecks()
                        }
                  }
        }

        stage ('Testing') {
                 steps{
                     sh "echo Testing in-progress"
                }
        }
}