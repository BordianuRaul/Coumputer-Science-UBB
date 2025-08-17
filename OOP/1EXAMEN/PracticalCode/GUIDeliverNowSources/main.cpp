#include "GUIDeliverNow.h"
#include <QtWidgets/QApplication>

#include "RepoCourier.h"
#include "RepoPackage.h"
#include "Service.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    
    auto repoCourier = new RepoCourier("couriers.txt");
    auto repoPack = new RepoPackage("packs.txt");

    auto service = new Service(*repoCourier, *repoPack);

    auto couriers = service->getCouriers();

    for (auto c : couriers)
    {
        auto window = new GUIDeliverNow{*service};
        
        window->setWindowTitle(QString::fromStdString(c.getName()));
        window->setZone(c.getZone());
        window->populate();
        window->show();
    }

    auto window = new GUIDeliverNow{ *service };
    window->setWindowTitle("Courier Company");
    window->populateCompany();
    window->show();

    return a.exec();
}
