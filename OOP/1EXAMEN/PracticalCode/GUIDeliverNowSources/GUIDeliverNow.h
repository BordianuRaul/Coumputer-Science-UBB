#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_GUIDeliverNow.h"
#include "Service.h"
#include "Observer.h"

class GUIDeliverNow : public QMainWindow, public Observer
{
    Q_OBJECT

public:
    GUIDeliverNow(Service& service, QWidget *parent = nullptr);
    void setZone(pair<int, int> zone);
    void populateCompany();

    void populate();
    void handleAdd();
    void connectAddSlot();
    
    void update();

    ~GUIDeliverNow();
    void populateComboBox();
    void handleDeliverButton();
    void connectDeliverButton();

private:
    Ui::GUIDeliverNowClass ui;
    Service& service;
};
