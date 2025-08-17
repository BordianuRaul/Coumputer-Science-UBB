#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_GUIResearchGrant.h"
#include "Service.h"
#include <qabstractitemmodel.h>
#include "Observer.h"

class GUIResearchGrant : public QMainWindow, public Observer
{
    Q_OBJECT

public:
    GUIResearchGrant(Service& service, QAbstractItemModel* model , QWidget *parent = nullptr);
    void setLabel(string data);
    void handleAddButton();
    void connectAddButton();
    void update();
    void setVisible();

    ~GUIResearchGrant();

private:
    Ui::GUIResearchGrantClass ui;
    Service& service;
    QAbstractItemModel* model;
};
