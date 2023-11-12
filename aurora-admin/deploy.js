require('dotenv').config({ path: __dirname + '/.env.local' })
const client = require('scp2')
const ora = require('ora')
const chalk = require('chalk')
const spinner = ora(chalk.green('Deploying the project to server...'))
const Client = require('ssh2').Client
const conn = new Client()
conn
  .on('ready', () => {
    conn.exec('rm -rf /web/admin', (err, stream) => {
      if (err) throw err
      stream
        .on('close', () => {
          spinner.start()
          client.scp(
            './dist/',
            {
              host: process.env.VUE_APP_HOST,
              port: process.env.VUE_APP_PORT,
              username: process.env.VUE_APP_USERNAME,
              password: process.env.VUE_APP_PASSWORD,
              path: process.env.VUE_APP_PATH
            },
            (err) => {
              if (!err) {
                spinner.stop()
                console.log(chalk.green('Project deployed！'))
              } else {
                console.log(chalk.red(err))
              }
            }
          )
          conn.end()
        })
        .on('data', () => {})
        .stderr.on('data', () => {})
    })
  })
  .on('error', async (err) => {
    console.log(chalk.red('Client connection error' + err.toString()))
  })
  .connect({
    host: process.env.VUE_APP_HOST,
    port: process.env.VUE_APP_PORT,
    username: process.env.VUE_APP_USERNAME,
    password: process.env.VUE_APP_PASSWORD
  })
