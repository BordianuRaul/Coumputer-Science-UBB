#include "GUIDeliverNow.h"

GUIDeliverNow::GUIDeliverNow(Service& service, QWidget *parent)
    : service(service), QMainWindow(parent)
{
    ui.setupUi(this);
    this->service.addObserver(this);
    this->connectAddSlot();
    this->populateComboBox();
    this->connectDeliverButton();
}

void GUIDeliverNow::setZone(pair<int, int> zone)
{
    string stringZone = to_string(zone.first) + " " + to_string(zone.second);
    this->ui.zoneLabel->setText(QString::fromStdString(stringZone));
}

void GUIDeliverNow::populateCompany()
{

    vector<Package> packs = this->service.getAllPacks();
    this->ui.listWidget->clear();

    for (auto p : packs)
    {
        

        QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(p.toString()));

        if (p.getDeliveryStatus() == true)
        {

            QBrush greenBrush(Qt::green);
            item->setBackground(greenBrush);
        }
        
        this->ui.listWidget->addItem(item);
    }

}

void GUIDeliverNow::populate()
{
    this->ui.listWidget->clear();

    auto stringTitle = this->windowTitle().toStdString();

    auto courier = this->service.getCourierByName(stringTitle);

    auto packs = this->service.getPacksForCourier(courier);

    for (auto p : packs)
    {
        this->ui.listWidget->addItem(QString::fromStdString(p.toString()));
    }

}

void GUIDeliverNow::handleAdd()
{
    string recipient = this->ui.recipientLineEdit->text().toStdString();
    string streetName = this->ui.streetName->text().toStdString();
    int streetNr = this->ui.streetNumber->text().toInt();
    pair<string, int> street;
    street.first = streetName;
    street.second = streetNr;

    int locationC = this->ui.locationCenter->text().toInt();
    int locationR = this->ui.locationRadius->text().toInt();
    pair<int, int> location;
    location.first = locationC;
    location.second = locationR;
    
    this->service.addPack(recipient, street, location, false);

    this->service.notify();

}

void GUIDeliverNow::connectAddSlot()
{
    QObject::connect(this->ui.pushButton, &QPushButton::clicked, this, &GUIDeliverNow::handleAdd);
}

void GUIDeliverNow::update()
{
    
    
    this->populate();
    this->populateComboBox();
    if (this->windowTitle().toStdString() == "Courier Company") this->populateCompany();
}

GUIDeliverNow::~GUIDeliverNow()
{}

void GUIDeliverNow::populateComboBox()
{
    this->ui.comboBox->clear();
    vector<string> streets = this->service.getAllStreets();

    for (auto s : streets)
    {
        this->ui.comboBox->addItem(QString::fromStdString(s));
    }
}

void GUIDeliverNow::handleDeliverButton()
{
    auto item = ui.listWidget->currentItem();

    int row = ui.listWidget->row(item);

    auto stringTitle = this->windowTitle().toStdString();

    auto courier = this->service.getCourierByName(stringTitle);

    auto packs = this->service.getPacksForCourier(courier);

    this->service.changeStatus(packs[row]);

    this->service.notify();

}

void GUIDeliverNow::connectDeliverButton()
{
    QObject::connect(this->ui.deliverButton, &QPushButton::clicked, this, &GUIDeliverNow::handleDeliverButton);
}
