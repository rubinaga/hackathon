import { Backdrop, CircularProgress } from "@mui/material"
import { useSelector } from "react-redux"
import { RootState } from "@utils/store"
import { redirectIfNotAuthorized } from "@utils/authorization"

const Loader = () => {
    const loaderValue = useSelector((state: RootState) => state.data.showLoader)
    return (
        <>
            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 999 }}
                open={loaderValue}
            >
                <CircularProgress color="inherit" />
            </Backdrop>
        </>
    )
}
export default Loader