#pragma once
#include "Service.h"
#include <QAbstractTableModel>

class Model : public QAbstractTableModel
{
private:
	Service& service;

public:

	Model(Service& service);

	int rowCount(const QModelIndex& parent = QModelIndex()) const;

	int columnCount(const QModelIndex& parent = QModelIndex()) const;

	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;

	bool insertRows(int row, int count, const QModelIndex& parent = QModelIndex());
};

