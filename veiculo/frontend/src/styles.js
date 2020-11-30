
const drawerWidth = 240;

export default (theme) => ({
    root: {
        display: 'flex',
    },
    toolbar: {
        paddingRight: 24, // keep right padding when drawer closed
    },
    toolbarIcon: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: '0 8px',
        ...theme.mixins.toolbar,
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
    },

    menuButton: {
        marginRight: 36,
    },
    menuButtonHidden: {
        display: 'none',
    },
    title: {
        flexGrow: 1,
    },
    appBarSpacer: theme.mixins.toolbar,
    content: {
        flexGrow: 1,
        height: '100vh',
        overflow: 'auto',
    },
    container: {
        paddingTop: theme.spacing(4),
        paddingBottom: theme.spacing(4),
    },
    spacing: {
        padding: theme.spacing(4),
    },
    buttonSave: {
        marginTop:10
    },
    paper: {
        padding: theme.spacing(2),
        display: 'flex',
        overflow: 'auto',
        flexDirection: 'column',
    },
    iconButton: {
        padding: 10,
    },
    input: {
        marginLeft: theme.spacing(1),
        flex: 1,
    },
    fixedHeight: {
        height: 240,
    },
    pesquisar: {
        width: '100%',
        padding: '2px 4px',
        display: 'flex',
        alignItems: 'center',
    },
    link: {
        textDecoration: 'none',
        color: '#FFF'
    },
    inputSpacing: {
        marginRight: 10
    },
    textarea: {
        marginTop:15,
        width: '100%'
    },
    rootCard: {
        minWidth: 275,
    },
    bullet: {
        display: 'inline-block',
        margin: '0 2px',
        transform: 'scale(0.8)',
    },
    titleCard: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
});
