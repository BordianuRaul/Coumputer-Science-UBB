#include "Volunteering.h"


Volunteering::Volunteering(Service& newService, QAbstractItemModel* model, QAbstractItemModel* unmatchedModel, QWidget *parent)
    : QMainWindow(parent), model(model), unmatchedModel(unmatchedModel), service(newService)
{
    ui.setupUi(this);

    ui.depVolunteers->setModel(this->model);
    ui.depVolunteers->resizeColumnsToContents();
    ui.unasignedVolunteers->setModel(this->unmatchedModel);
    ui.unasignedVolunteers->resizeColumnsToContents();


    this->service.addObserver((Observer*)(model));
    this->service.addObserver((Observer*)(unmatchedModel));

    this->connectSignalAndSlots();
    QObject::connect(&this->service, &Service::notifyObservers, this, &Volunteering::updateView);



}



Volunteering::~Volunteering()
{}

void Volunteering::setDescription(const QString & description)
{
    
    this->ui.depDescription->setText(description);

}

void Volunteering::connectSignalAndSlots()
{
    QObject::connect(this->ui.pushButton, &QPushButton::clicked, this, &Volunteering::addVolunteer);


    //set data

}

void Volunteering::addVolunteer()
{
    string name = this->ui.nameLineEdit->text().toStdString();
    string email = this->ui.emailLineEdit->text().toStdString();

    string line = this->ui.preferenceLineEdit->text().toStdString();

    vector<string> preferences;

    stringstream ss(line);
    string aux;
    while (getline(ss, aux, ','))
    {
        preferences.push_back(aux);
    }

    this->service.addVolunteer(name, email, preferences);
    

    
}

void Volunteering::updateView()
{
    ui.depVolunteers->resizeColumnsToContents();
    ui.unasignedVolunteers->resizeColumnsToContents();
}
