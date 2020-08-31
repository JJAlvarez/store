#!groovy
pipeline {
    agent none
   stages {     
    stage('Maven Install') {
      agent {         
       docker {          
         image 'maven:3.5.0'         
     }       
  }       
  steps {
       sh 'mvn clean install'
       }
     }
       stage ('docker image build')
        {
            steps {
                   
                        sh 'mvn dockerfile:build'
                         
                  }
          }
   }
 }
