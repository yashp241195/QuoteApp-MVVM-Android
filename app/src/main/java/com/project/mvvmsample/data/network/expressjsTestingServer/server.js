const express = require('express')

var bodyParser = require('body-parser')

var jsonParser = bodyParser.json()

// create application/x-www-form-urlencoded parser
var urlencodedParser = bodyParser.urlencoded({ extended: false })

const app = express()
const port = 3000

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.post('/user',(req,res)=>{

    res.send("post working")
    // var email = req.params('email')
    // var password = req.params('password')

    // res.json({
        // email: email,
        // password: password
    // })

})

app.post('/login',urlencodedParser,(req,res)=>{

  var body = req.body;
  console.log(body)
  res.json({
    isSuccessful:true,
    message: "successfully login Yash",
    user:{
      id:1,
      name:"Yash",
      email:req.body.email,
      token : "alpha12312"
    }

  })
 })

  app.post('/signup',urlencodedParser,(req,res)=>{

    var body = req.body;
    console.log(body)
    res.json({
      isSuccessful:true,
      message: "successfully signup Yash",
      user:{
        id:1,
        name:"Yash Pratap",
        email:req.body.email,
        token : "alpha12312"
      }

    })
  })

  app.get('/quotes',(req,res)=>{

    res.json({
      isSuccessful:true,
      message: "successfully quotes loaded",
      quotes:[
        {id:1,quote:"Prediction is not knowing", author:"Yash"},
        {id:2, quote:"Acceptance heals, not sympathy", author:"Yash"},
        {id:3, quote:"Identity defines everything", author:"Yash"},
        {id:4, quote:"Logic never goes beyond knowledge", author:"Yash"},
      ]

    })
  })


  // res.send("post working")
  // var email = req.params('email')
  // var password = req.params('password')

  // res.json({
      // email: email,
      // password: password
  // })

// })


app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})