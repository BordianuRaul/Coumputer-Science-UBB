#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Volunteering.h"
#include "VolunteersTableModel.h"
#include <qabstractitemmodel.h>

class Volunteering : public QMainWindow
{
    Q_OBJECT

public:
    Volunteering(Service&, QAbstractItemModel*, QAbstractItemModel*, QWidget *parent = nullptr);
    ~Volunteering();

    void setDescription(const QString& description);

private:
    Ui::VolunteeringClass ui;
    QAbstractItemModel* model;
    QAbstractItemModel* unmatchedModel;
    Service& service;
    
    void connectSignalAndSlots();

    void addVolunteer();
    void updateView();
};
