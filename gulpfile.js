var gulp = require('gulp');
var minifyCSS = require('gulp-csso');
var minify = require('gulp-minify');
var bundleFiles = require('gulp-bundle-files');

var src = 'src/main/resources/static';
var destination = 'src/main/resources/static/dist';

gulp.task('clean', function() {
    gulp.src(destination + '/*').pipe(rm());
});

gulp.task('css', function(){
    return gulp.src(
        [ src + '/css/*.css',
          src+'/css/vendor/*.css'
        ])
        .pipe(minifyCSS())
        .pipe(gulp.dest(destination + '/css'))
});

gulp.task('compress', function() {
    gulp.src(src + '/js/*.js')
        .pipe(minify({
            ext:{
                src:'-debug.js',
                min:'.js'
            },
            exclude: ['tasks'],
            ignoreFiles: ['.combo.js', '-min.js']
        }))
        .pipe(gulp.dest(destination + '/js'))
});

gulp.task('bundle', function() {
    bundleFiles({
        "parentTaskName": "bundle",
        "concat": {
            "active": true,
            "config": {}
        },
        "uglify": {
            "active": false,
            "config": {}
        },
        "sourcemap": {
            "active": true,
            "config": {}
        },
        "destinationFolder": destination,
        "newLine": ";",
        "files": {
            "/js/test.js": [
                "src/main/resources/static/js/calendar.js",
                "src/main/resources/static/js/fullcalendar.js"
            ],
            "/css/test.css": [
                "src/main/resources/static/css/calendar.css"
            ]
        }
    });
});

gulp.task('default', [ 'css', 'compress' ]);