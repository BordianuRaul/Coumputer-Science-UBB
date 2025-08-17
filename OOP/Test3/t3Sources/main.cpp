#include "SearchEngine.h"
#include <QtWidgets/QApplication>
#include <QLabel>
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    Service service;

    SearchEngine w(service);
    w.show();
    return a.exec();
}
