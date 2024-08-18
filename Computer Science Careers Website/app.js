const express = require('express');
const bodyParser = require('body-parser');
const e = require('express');
const app = express();
const port = 3000;
const fs = require('fs');

// Parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))
app.use(express.json())

// const fs   = require('fs'),
const http = require('http');
const text = require('body-parser/lib/types/text');
const { parse } = require('path');
// const { isNumberObject } = require('util/types');

app.get('/aboutPage', (req, res, next) => {

    let options = {
    root: __dirname,
        headers: {
            'Content-Type': 'text/html; charset=UTF-8'
        }
    };

    res.sendFile('/aboutPage.html', options, function (err) {
        if (err) {
             next(err);
        } else {
            console.log('Sent: aboutPage.html');
        }    
    });
});

app.post('/api/users/create', (req, res) => {
    let name = req.body.name;
    let email = req.body.email;
    let date = new Date(req.body.dateReview);

    let score = req.body.scoreReview;

    //Date format
    const handleDate = (date) => {
        let data = new Date(date)
        let month = data.getMonth() + 1
        let day = data.getDate()
        let year = data.getFullYear()
        if(day<=9)
            day = '0' + day
        if(month<10)
        month = '0' + month
        const postDate = day + '/' + month + '/' + year
        return postDate
    }

    console.log('Saved to the database.');
    
        res.send([
        'Review completed', 
        `Name: ${name}`,
        `Email: ${email}`,
        `Date: ${handleDate(date)}`,
        `Score: ${score}`].join('<br>')); //)
        // '<link rel="stylesheet" href="cssHome.css"></link>',
        // '<a href="/aboutPage">Back</a>'
        
       

        let content = name + " " + email + "\n" +
                     handleDate(date) + " " + "Score : " + score + "\n\n";
        // let content = name + " " + email + "\n";
        fs.appendFile('./comment.txt', content, (err) => {
            if (err) {
                console.error(err);
                return
            }
            console.log('File was written sucessfully.');
0        });
});

app.listen(port, () => {
    console.log(`Listening at http://localhost:${port}`);
});
