def lintchecks(){
    sh ***
              echo performing Pylint for ${COMPONENT}
              pip3 install Pylint
              pylint *.py
              echo lint checks completed for ${COMPONENT}
    ***
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
            }         /////
        }
        stage ('Code Compile') {
            steps{
                sh "mvn clean compile"
            }
        }
    }
}
}