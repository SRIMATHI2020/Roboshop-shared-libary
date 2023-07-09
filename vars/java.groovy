def lintchecks(){
    sh  "echo performing litchecks for ${COMPONENT}"
    sh  "mvn checkstyle:check || true"
    sh  "echo lint checks completed for ${COMPONENT} "
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
                 }
            }        
        }
    }
}
        stage ('Sonar Checks') {
                  steps{
                     script {
                          common.sonarChecks()
                }
            }
}