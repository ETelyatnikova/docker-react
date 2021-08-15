def gv
pipeline {
 agent any
 parameters {
  choice(name:'VERSION', choices:['1.1.0', '1.2.0', '1.3.0'], description: '')
  booleanParam(name:'executeTests', defaultValue: true, description:'')
 }
  stages {
    stage("init") {
      steps {
       script {
        gv = load 'script.groovy'
       }
      }
    }

   stage("buildApp") {
     agent {
       docker {
        image 'node:lts-buster-slim'
        args '-p 3000:3000'
        reuseNode true
       }
      }

      steps {
       script {
        gv.buildApp()
       }
      }
    }

   stage("buildImage") {
     steps {
       script {
        gv.buildImage()
       }
      }
    }
    
    stage("test") {
     when {
      expression {
       params.executeTests
      }
     }
      steps {
       script {
        gv.testApp()
       }
      }
    }
    
    stage("deploy") {
      steps {
       script {
        gv.deployApp()
       }
      }
    }
  }
}
