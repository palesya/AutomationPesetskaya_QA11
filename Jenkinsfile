pipeline {
    agent any

    tools{
        maven "MAVEN"
    }

    environment{
        SUITE="src/test/resources/${params.Suite}.xml"
    }


    stages {
        stage('Build') {
            steps {
               //git branch:'master',url:'https://github.com/palesya/AutomationPesetskaya_QA11.git'
               bat 'mvn clean'
            }
        }
        stage('Test run') {
            steps {
                echo '-----------------------------Started${evn.SUITE}--------------------'
               bat 'mvn clean test -Dsuite=${env.SUITE}'
            }
        }
        stage('Reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                        ])
                        }
            }
        }
    }
}