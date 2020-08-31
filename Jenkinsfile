pipeline {
    agent any
    stages {
        stage ('Maven Install') {
            agent {
               docker {
                 image 'maven:3.5.0'
                }
        }
        stage ('build app test') {
            steps {
                sh 'mvn clean install -DskipTests=true '
            }
        }

        stage ('docker image build')
                {
                    steps {

                        sh 'mvn dockerfile:build'

                    }
                }
        stage ('docker image push to Docker Hub') {
            steps {
                sh 'mvn dockerfile:push'
            }
        }
    }
}
