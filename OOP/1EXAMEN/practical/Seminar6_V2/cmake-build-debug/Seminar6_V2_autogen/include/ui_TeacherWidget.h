/********************************************************************************
** Form generated from reading UI file 'TeacherWidget.ui'
**
** Created by: Qt User Interface Compiler version 6.5.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TEACHERWIDGET_H
#define UI_TEACHERWIDGET_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QTableView>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_TeacherWidgetClass
{
public:
    QVBoxLayout *verticalLayout;
    QTableView *studentsTabelView;

    void setupUi(QWidget *TeacherWidgetClass)
    {
        if (TeacherWidgetClass->objectName().isEmpty())
            TeacherWidgetClass->setObjectName("TeacherWidgetClass");
        TeacherWidgetClass->resize(546, 400);
        verticalLayout = new QVBoxLayout(TeacherWidgetClass);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName("verticalLayout");
        studentsTabelView = new QTableView(TeacherWidgetClass);
        studentsTabelView->setObjectName("studentsTabelView");

        verticalLayout->addWidget(studentsTabelView);


        retranslateUi(TeacherWidgetClass);

        QMetaObject::connectSlotsByName(TeacherWidgetClass);
    } // setupUi

    void retranslateUi(QWidget *TeacherWidgetClass)
    {
        TeacherWidgetClass->setWindowTitle(QCoreApplication::translate("TeacherWidgetClass", "TeacherWidget", nullptr));
    } // retranslateUi

};

namespace Ui {
    class TeacherWidgetClass: public Ui_TeacherWidgetClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TEACHERWIDGET_H
