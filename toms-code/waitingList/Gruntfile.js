'use strict';
module.exports = function (grunt) {

	// Project configuration.
	grunt.initConfig({

		concat: {
			options: {
				banner: '(function(){',
				separator: ';',
				footer: '}());'
			},
			dist: {
				src: ['public/js/request.js', 'public/js/waitingList.js', 'public/js/ui.js'],
				dest: 'public/js/wl.js',
			},
		},
		uglify: {
			compress: {
				files: {
					'public/js/wl.min.js': ['public/js/wl.js']
				}
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');

	grunt.registerTask('default', ['concat', 'uglify']);

};