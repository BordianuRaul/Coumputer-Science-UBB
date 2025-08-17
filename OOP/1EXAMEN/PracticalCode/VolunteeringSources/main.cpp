#include "Volunteering.h"
#include <QtWidgets/QApplication>
#include <iostream>
#include <vector>
#include "RepoDep.h"
#include "RepoVol.h"
#include "Service.h"
#include "VolunteersTableModel.h"
#include <QSortFilterProxyModel>

using namespace std;

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);


    RepoDep repo("Departaments.txt");

    RepoVol repoVol("Volunteers.txt");
    Service service(&repo, &repoVol);

    VolunteersTableModel* model = new VolunteersTableModel{service};

    vector<Volunteering*> windows;

    vector<Departament> deps = service.getDepartaments();

    vector<QSortFilterProxyModel*> models;
    int i = 0;
    for (auto n : deps)
    {
        QSortFilterProxyModel* filteredModel = new QSortFilterProxyModel{ };
        filteredModel->setSourceModel(model);
        filteredModel->setFilterRegularExpression(QRegularExpression(QString::fromStdString(deps[i].getName()), QRegularExpression::CaseInsensitiveOption));
        filteredModel->setFilterKeyColumn(3);

        QSortFilterProxyModel* unmatchModel = new QSortFilterProxyModel{ };
        unmatchModel->setSourceModel(model);
        unmatchModel->setFilterRegularExpression(QRegularExpression(QString::fromStdString("^$")));
        unmatchModel->setFilterKeyColumn(3);

 
        models.push_back(filteredModel);

        windows.push_back(new Volunteering {service, filteredModel, unmatchModel});
        i++;

    }

    i = 0;
    for (auto window : windows)
    {
        window->setWindowTitle(QString::fromStdString(deps[i].getName()));
        window->setDescription(QString::fromStdString(deps[i].getDescription()));

        window->show();
        i++;
    }

    return a.exec();
}
