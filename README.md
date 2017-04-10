# skyhub
This project was created using Spring Boot and MongoDB.
I wanted to try starting a spring boot project from scratch and see if it was as easy as people tell.

It consumes a webservice endpoint (http://54.152.221.29/images.json) that returns a JSON of
photos and generates three different dimensions for each photo (small, medium and large).

Also it provides a webservice endpoint that lists (in JSON format) all the ten photos with their
respective formats, providing their URLs.

To resize the images I used a library called Thumbnailator (https://github.com/coobird/thumbnailator).

