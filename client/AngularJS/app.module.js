/**
 * Created by Loukik on 27-Sep-16.
 */
(function() {


    angular.module('app',['ngMessages'])
        .run(moduleRun);

    function moduleRun() {
        console.log('Movieflix started');
    }
})();