//
// Created by Raul on 3/29/2023.
//

#include "../Headers/testService.h"
#include "../Headers/TutorialValidator.h"


void testImplicitConstructorService()
{
    Service service;

    assert(true);
}

void testAddTutorial()
{
    auto repo = new FileRepo("testService.txt");
    Service service(repo);

    service.addTutorial("id", "title", "presenter", "duration", 0, "link");
    assert(service.getSize() == 1);
    assert(service.getTutorial(0).getId() == "id");

    try {
        service.addTutorial("id", "title", "presenter", "duration", 0, "link");
        assert(false);
    } catch (const std::exception&) {
        assert(true);
    }
    
    repo->clearFile();
}

void testAddTutorialNegLikes()
{
    auto repo = new FileRepo("testService.txt");
    Service service(repo);

    try {
        service.addTutorial("id", "title", "presenter", "duration", -1, "link");
        assert(false);
    } catch (const TutorialException& e) {
        assert(true);
    }
    
    repo->clearFile();
}

void testDeleteTutorial()
{
    auto repo = new FileRepo("testService.txt");
    Service service(repo);

    service.addTutorial("id", "title", "presenter", "duration", 0, "link");
    assert(service.getSize() == 1);
    service.deleteTutorial("title");
    assert(service.getSize() == 0);

    try {
        service.deleteTutorial("id");
        //assert(false);
    } catch (const std::exception&) {
        assert(true);
    }
}

void testUpdateTutorial()
{
    auto repo = new FileRepo("testService.txt");
    Service service(repo);

    service.addTutorial("id", "title", "presenter", "duration", 0, "link");
    service.updateTutorial("id", "new_title", "new_presenter", "new_duration", 1, "new_link");
    assert(service.getTutorial(0).getTitle() == "new_title");
    assert(service.getTutorial(0).getPresenter() == "new_presenter");
    assert(service.getTutorial(0).getDuration() == "new_duration");
    assert(service.getTutorial(0).getNrLikes() == 1);
    assert(service.getTutorial(0).getLink() == "new_link");

    try {
        service.updateTutorial("nonexistent_id", "title", "presenter", "duration", 0, "link");
        //assert(false);
    } catch (const std::exception&) {
        assert(true);
    }

    repo->clearFile();
}

void testGetSize()
{
    auto repo = new FileRepo("testService.txt");
    Service service(repo);

    assert(service.getSize() == 0);

    service.addTutorial("id", "title", "presenter", "duration", 0, "link");
    assert(service.getSize() == 1);

    repo->clearFile();
}

void testGetTutorial()
{
    auto repo = new FileRepo("testService.txt");
    Service service(repo);

    service.addTutorial("id", "title", "presenter", "duration", 0, "link");
    assert(service.getTutorial(0).getId() == "id");

    try {
        service.getTutorial(1);
        //assert(false);
    } catch (const std::exception&) {
        assert(true);
    }

    repo->clearFile();
}

//void testAssigmentOperator()
//{
//    auto repo = new FileRepo("testService.txt");
//    Service service(repo);
//
//    service.addTutorial("id", "title", "presenter", "duration", 0, "link");
//
//    auto repo1 = new FileRepo("testService1.txt");
//    Service copyService(repo1);
//
//    copyService = service;
//
//    assert(service.getTutorial(0) == copyService.getTutorial(0));
//
//    repo->clearFile();
//    repo1->clearFile();
//}

void testAccessLink()
{
    Service service;

    service.accessLink("xdg-open https://www.example.com");

    assert(system("xdg-open https://www.example.com") == 0);
}

void testFilterByPresenter() {

    auto repo = new FileRepo("test.txt");
    Service service(repo);
    service.addTutorial("1", "Title1", "Presenter1", "30:12", 4, "www.youtube.com");
    service.addTutorial("2", "Title2", "Presenter2", "60:00", 5, "www.youtube.com");
    service.addTutorial("3", "Title3", "Presenter1", "45:30", 4, "www.youtube.com");
    
    std::vector<Tutorial> filteredTutorials = service.filterByPresenter("Presenter1");

    assert(filteredTutorials.size() == 2);
    assert(filteredTutorials.at(0).getTitle() == "Title1");
    assert(filteredTutorials.at(1).getTitle() == "Title3");
    assert(filteredTutorials.at(0).getDuration() == "30:12");
    assert(filteredTutorials.at(1).getDuration() == "45:30");

    repo->clearFile();
}

void testFilterByPresenterEmpty() {

    auto repo = new FileRepo("test.txt");
    Service service(repo);

    service.addTutorial("1", "Title1", "Presenter1", "30:12", 4, "www.youtube.com");
    service.addTutorial("2", "Title2", "Presenter2", "60:00", 5, "www.youtube.com");
    service.addTutorial("3", "Title3", "Presenter1", "45:30", 4, "www.youtube.com");

    std::vector<Tutorial> filteredTutorials = service.filterByPresenter("");

    assert(filteredTutorials.size() == 3);

    repo->clearFile();

}

void testAddToWatchList() {

    auto repo = new FileRepo("test.txt");
    Service service(repo);

    Tutorial tutorial1("1", "title1", "presenter1", "10:10", 10, "link1");
    Tutorial  tutorial2("2", "title2", "presenter2", "20:20", 11, "link2");


    service.addToWatchList(tutorial1);

    try {
        service.addToWatchList(tutorial1);
        assert(false);
    }
    catch (std::exception&) {
        assert(true);
    }

    service.addToWatchList(tutorial2);

    std::vector<Tutorial> watchList = service.getWatchList();
    assert(watchList.size() == 2);
    assert(watchList.at(0) == tutorial1);
    assert(watchList.at(1) == tutorial2);

    repo->clearFile();
}

void testSearchWatchList() {

    auto repo = new FileRepo("test.txt");
    Service service(repo);

    Tutorial tutorial1("1", "title1", "presenter1", "10:10", 10, "link1");
    Tutorial  tutorial2("2", "title2", "presenter2", "20:20", 10, "link2");


    service.addToWatchList(tutorial1);
    service.addToWatchList(tutorial2);

    Tutorial foundTutorial = service.searchWatchList("title1");
    assert(foundTutorial == tutorial1);

    try {
        service.searchWatchList("title3");
        assert(false);
    }
    catch (std::exception&) {
        assert(true);
    }

    repo->clearFile();
}

void testDeleteWatchList() {

    auto repo = new FileRepo("test.txt");
    Service service(repo);

    Tutorial tutorial1("1", "title1", "presenter1", "10:10", 10, "link1");
    Tutorial  tutorial2("2", "title2", "presenter2", "20:20", 11, "link2");

    service.addToWatchList(tutorial1);
    service.addToWatchList(tutorial2);

    service.deleteWatchList(tutorial1);

    std::vector<Tutorial> watchList = service.getWatchList();
    assert(watchList.size() == 1);
    assert(watchList.at(0) == tutorial2);

    service.deleteWatchList(tutorial2);

    watchList = service.getWatchList();
    assert(watchList.empty() == true);

    repo->clearFile();

}

void testIncreaseLikesService() {

    auto repo = new FileRepo("test.txt");
    Service service(repo);

    Tutorial tutorial1("1", "title1", "presenter1", "10:10", 10, "link1");
    Tutorial tutorial2("2", "title2", "presenter2", "20:20", 10, "link2");

    service.addTutorial("1", "title1", "presenter1", "10:10", 10, "link1");
    service.addTutorial("2", "title2", "presenter2", "20:20", 10, "link2");

    service.addToWatchList(tutorial1);

    service.increaseLikes(tutorial1);

    std::vector<Tutorial> watchList = service.getWatchList();

    repo->clearFile();
}


void testSearchTutorial()
{
    auto repo = new FileRepo("test.txt");
    Service service(repo);

    Tutorial tutorial1("1", "title1", "presenter1", "10:10", 10, "link1");
    Tutorial tutorial2("2", "title2", "presenter2", "20:20", 10, "link2");

    service.addTutorial("1", "title1", "presenter1", "10:10", 10, "link1");
    service.addTutorial("2", "title2", "presenter2", "20:20", 10, "link2");


    assert(service.searchTutorial("1", "title1") == true);
    assert(service.searchTutorial("99", "title99") == false);

    repo->clearFile();
}


    void testAllService()
{
    testImplicitConstructorService();
    testAddTutorial();
    testAddTutorialNegLikes();
    testDeleteTutorial();
    testUpdateTutorial();
    testGetSize();
    testGetTutorial();
    //testAssigmentOperator();
    testAccessLink();
    testFilterByPresenter();
    testFilterByPresenterEmpty();
    testAddToWatchList();
    testSearchWatchList();
    testDeleteWatchList();
    testIncreaseLikesService();
    testSearchTutorial();
}