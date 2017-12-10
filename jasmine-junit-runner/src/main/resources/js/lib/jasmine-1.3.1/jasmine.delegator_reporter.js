/*
Copyright (c) 2008-2013 Pivotal Labs

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
jasmineRequire.delegateJunit = function(j$) {
	j$.DelegatorJUnitReporter = jasmineRequire.DelegatorJUnitReporter(j$);
};

jasmineRequire.DelegatorJUnitReporter = function(j$) {

	function DelegatorJUnitReporter(options) {
		// var env = options.env || {};
		this.javaReporter = jasmine.DelegatorJUnitReporter.javaReporter;

		this.initialize = function() {
			console.log("initialize");
		};

		this.jasmineStarted = function(options) {
			console.log("jasmineStarted:" + JSON.stringify(options));
		};

		this.suiteStarted = function(result) {
			console.log("suiteStarted:" + JSON.stringify(result));
		};

		this.suiteDone = function(result) {
			console.log("suiteDone:" + JSON.stringify(result));
		};

		this.specStarted = function(result) {
			console.log("specStarted:" + JSON.stringify(result));
			console.log(this.javaReporter)
		};

		this.specDone = function(result) {
			console.log("specDone:" + JSON.stringify(result));
		};

		this.jasmineDone = function() {
			console.log("jasmineDone");
		};

		return this;
	}

	return DelegatorJUnitReporter;
};
