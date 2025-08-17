#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_SearchEngine.h"
#include "Service.h"
#include <QPushButton>
#include <QMessageBox>

class SearchEngine : public QMainWindow
{
    Q_OBJECT

public:
    SearchEngine(Service&, QWidget *parent = nullptr);
    ~SearchEngine();

    void populate();

private:
    Ui::SearchEngineClass ui;

    Service service;

    void handleSearch(QString);

    void connectSearch();

    void handleBestMatch();

    void connectBestMatch();

};
