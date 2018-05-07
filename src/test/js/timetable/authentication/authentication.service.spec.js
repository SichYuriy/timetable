describe('authenticationService', function () {
    beforeEach(module('timetableApp.authentication'));

    let authenticationRepositoryMock;
    let authenticationService;

    let user;
    let repositoryResult;

    beforeEach(function () {
        user = {
            username: 'John_Snow',
            password: '123'
        };
        repositoryResult = {
            then: function () {
            }
        };

        authenticationRepositoryMock = {
            login: jasmine.createSpy().and.returnValue(repositoryResult),
            logout: jasmine.createSpy().and.returnValue(repositoryResult),
            getCurrentUser: jasmine.createSpy().and.returnValue(repositoryResult),
            register: jasmine.createSpy().and.returnValue(repositoryResult)
        };

        module(function($provide) {
            $provide.value('authenticationRepository', authenticationRepositoryMock);
        });

        inject(function ($injector) {
            authenticationService = $injector.get('authenticationService');
        })
    });

    it('should delegate login to the repository', function () {
        let actualResult = authenticationService.login(user);
        expect(authenticationRepositoryMock.login).toHaveBeenCalledWith(user);
        expect(actualResult).toEqual(repositoryResult);
    });

    it('should delegate logout to the repository', function () {
        authenticationService.logout();
        expect(authenticationRepositoryMock.logout).toHaveBeenCalled();
    });

    it('should delegate getCurrentUser to the repository', function () {
        authenticationService.getCurrentUser();
        expect(authenticationRepositoryMock.getCurrentUser).toHaveBeenCalled();
    });

    it('should delegate register to the repository', function () {
        authenticationService.register(user);
        expect(authenticationRepositoryMock.register).toHaveBeenCalledWith(user);
    });
});