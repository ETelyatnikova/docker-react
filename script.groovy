def buildApp() {
  echo 'building the app...'
  sh 'npm install'
  sh 'npm run build' 
}

def buildImage() {
  echo 'building the docker image...'
  docker.withRegistry('192.168.1.77:8083', 'nexus-docker-repo') {
    def myImage = docker.build("demo-app:${params.VERSION}")
    myImage.push()
  }
}

def testApp() {
  echo 'testing the app...'
}

def deployApp() {
  echo 'deploying the app...'
  echo "version ${params.VERSION}"
}

return this
