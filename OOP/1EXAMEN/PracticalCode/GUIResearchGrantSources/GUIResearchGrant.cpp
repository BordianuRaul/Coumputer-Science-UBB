#include "GUIResearchGrant.h"

GUIResearchGrant::GUIResearchGrant(Service& service, QAbstractItemModel* model ,QWidget *parent) : service(service),model(model), QMainWindow(parent)
{
    ui.setupUi(this);
    
    this->ui.tableView->setModel(model);
    this->connectAddButton();
    this->service.addObserver(this);

}

void GUIResearchGrant::setLabel(string data)
{
    this->ui.PositionLabel->setText(QString::fromStdString(data));
}

void GUIResearchGrant::handleAddButton()
{
    string title = this->ui.TitleLineEdit->text().toStdString();
    string description = this->ui.DescriptionLineEdit->text().toStdString();
    int duration = this->ui.DurationLineEdit->text().toInt();
    string creator = this->windowTitle().toStdString();
    string status = "proposed";

    this->service.addIdea(title, description, status, creator, duration);

    this->service.notify();
}

void GUIResearchGrant::connectAddButton()
{
    QObject::connect(this->ui.AddPushButton, &QPushButton::clicked, this, &GUIResearchGrant::handleAddButton);
}

void GUIResearchGrant::update()
{
    this->model->insertRow(this->model->rowCount(QModelIndex()));
}

void GUIResearchGrant::setVisible()
{
    this->ui.SaveAllPushButton->setVisible(false);
}

GUIResearchGrant::~GUIResearchGrant()
{}
