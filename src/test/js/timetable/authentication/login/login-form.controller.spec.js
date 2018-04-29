describe('LoginFormController', function () {
    beforeEach(module('timetableApp.authentication'));

    var $rootScope;
    var $controller;
    var $q;

    var deferredLogin;

    var $scope;
    var $window;
    var authenticationService;
    var ctrl;

    beforeEach(inject(function (_$rootScope_, _$controller_, _$q_) {
        $rootScope = _$rootScope_;
        $controller = _$controller_;
        $q = _$q_;
    }));

    beforeEach(function () {
        deferredLogin = $q.defer();

        $scope = $rootScope.$new();
        $window = {location: {}};
        authenticationService = {login: jasmine.createSpy().and.returnValue(deferredLogin.promise)};
    });

    beforeEach(function () {
        ctrl = $controller('LoginFormController', {
            $scope: $scope,
            $window: $window,
            authenticationService: authenticationService
        });
    });

    describe('initialization', function () {
        it('should init default username and password', function () {
            expect($scope.username).toEqual('');
            expect($scope.password).toEqual('');
        });

        it('should init login failed', function () {
            expect($scope.loginFailed).toBeFalsy();
        });
    });

    describe('submit login', function () {
        beforeEach(function () {
            $scope.username = 'John_Snow';
            $scope.password = '123';

            $scope.submitLogin();
        });

        it('should call authentication service' , function () {
            expect(authenticationService.login).toHaveBeenCalledWith({
                username: 'John_Snow',
                password: '123'
            });
        });

        describe('when login successful', function () {
            beforeEach(function () {
                deferredLogin.resolve();
                $scope.$apply();
            });

            it('should redirect to the dashboard', function () {
                expect($window.location.href).toEqual('#!/dashboard');
            });
        });

        describe('when user is not found', function () {
            beforeEach(function () {
                deferredLogin.reject();
                $scope.$apply();
            });

            it('should set loginFailed', function () {
                expect($scope.loginFailed).toBeTruthy();
            })
        });
    });
});