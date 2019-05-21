echo "Inicio"

USUARIO="$1"
PASSWORD="$2"

sh shiva-2016.04.25.v2.05.00-00.ActualizarVersion.sh $USUARIO $PASSWORD
sh shiva-2016.05.27.v2.05.00-02.ModificarTablas.sh $USUARIO $PASSWORD
sh shiva-2016.05.30.v2.05.00-02.ModificarTablas.sh $USUARIO $PASSWORD
sh shiva-2016.06.02.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.06.06.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.06.09.v2.05.00-02.ModificarTablas.sh $USUARIO $PASSWORD
sh shiva-2016.06.14.v2.05.00-02.ModificarTablas.sh $USUARIO $PASSWORD
sh shiva-2016.06.17.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.06.23.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.06.28.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.07.01.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.07.01.v2.05.00-05.Grants.sh $USUARIO $PASSWORD
sh shiva-2016.07.01.v2.05.00-06.Sinonimos.sh $USUARIO $PASSWORD
sh shiva-2016.07.04.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.07.05.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.07.06.v2.05.00-02.ModificarTablas.sh $USUARIO $PASSWORD
sh shiva-2016.07.12.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.07.18.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.07.19.v2.05.00-02.ModificarTablas.sh $USUARIO $PASSWORD
sh shiva-2016.07.22.v2.05.00-03.ModificarVistas.sh $USUARIO $PASSWORD
sh shiva-2016.08.23.v2.05.00-02.ModificarTablas.sh $USUARIO $PASSWORD

echo "Fin"
exit;