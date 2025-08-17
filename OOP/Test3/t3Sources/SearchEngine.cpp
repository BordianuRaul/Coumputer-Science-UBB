#include "SearchEngine.h"

SearchEngine::SearchEngine(Service& service, QWidget* parent)
    :service(service), QMainWindow(parent)
{
    ui.setupUi(this);

    this->populate();

    this->connectSearch();

    this->connectBestMatch();
}

SearchEngine::~SearchEngine()
{}

void SearchEngine::populate()
{

    this->ui.listWidget->clear();

    this->service.sortDocuments();

    for (auto& doc : this->service.getDocuments())
    {
        this->ui.listWidget->addItem(QString::fromStdString(doc.toString()));
    }
}

void SearchEngine::connectSearch()
{

    QObject::connect(this->ui.inputSearch, &QLineEdit::textChanged, this, &SearchEngine::handleSearch);

}

void SearchEngine::connectBestMatch()
{
    QObject::connect(this->ui.bestMatchButton, &QPushButton::clicked, this, &SearchEngine::handleBestMatch);
}

void SearchEngine::handleBestMatch()
{
    string searchString = this->ui.inputSearch->text().toStdString();

    string foundBestMatch = this->service.bestMatch(searchString);

    QString message = QString::fromStdString(foundBestMatch);

    QMessageBox::information(this, "Best match", message);

}

void SearchEngine::handleSearch(QString searchedString)
{
    this->ui.listWidget->clear();

    this->service.sortDocuments();

    for (auto& doc : this->service.getDocuments())
    {
        QString name = QString::fromStdString(doc.getName());
        QString keywords = QString::fromStdString(doc.getStringKeyWords());

        if (name.contains(searchedString, Qt::CaseInsensitive) || keywords.contains(searchedString, Qt::CaseInsensitive))
            this->ui.listWidget->addItem(QString::fromStdString(doc.toString()));
    }

}