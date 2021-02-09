(function () {

  var $usernameFld, $passwordFld
  var $firstNameFld, $lastNameFld, $roleFld
  var $removeBtn, $editBtn, $createBtn
  var $userRowTemplate, $updateBtn, $tbody
  var $searchBtn
  var selectedUser
  var userService =  new AdminUserServiceClient()


  $(main)

  var users = []

  function main() {
    $tbody = $("#wbdv-tbody")
    $userRowTemplate = $("#wbdv-template")
    $usernameFld = $("#usernameFld")
    $passwordFld = $("#passwordFld")
    $firstNameFld = $("#firstNameFld")
    $lastNameFld = $("#lastNameFld")
    $roleFld = $("#roleFld")

    $editBtn = $(".wbdv-edit")
    $createBtn = $(".wbdv-create")
    $removeBtn = $(".wbdv-remove")
    $updateBtn = $(".wbdv-update")
    $searchBtn = $(".wbdv-search")

    findAllUsers()

    // Listen to create user button
    $createBtn.click(createUser)
    $updateBtn.click(updateUser)
    $searchBtn.click(searchUsers)

  }

  function createUser() {
    if ($usernameFld.val() && $passwordFld.val() && $firstNameFld.val()
        && $lastNameFld.val() && $roleFld.val()) {
      var newUser = {
        username: $usernameFld.val(),
        password: $passwordFld.val(),
        firstName: $firstNameFld.val(),
        lastName: $lastNameFld.val(),
        role: $roleFld.val()
      }

      // Clear the input field
      $usernameFld.val('')
      $passwordFld.val('')
      $firstNameFld.val('')
      $lastNameFld.val('')

      userService.createUser(newUser).then(function (actualUser) {
        users.push(actualUser)
        renderUsers(users)
      })
    } else {
      alert("Inputs should not be empty.")
    }



  }


  function deleteUser(event) {
    var button = $(event.target)
    var index = button.attr("id")
    var theUserId = users[index]._id

    userService.deleteUser(theUserId).then(function (status){
      users.splice(index, 1)
      renderUsers(users)
    })
  }


  function selectUser(event) {
    var index = $(event.target).attr("id")
    selectedUser = users[index]
    $usernameFld.val(selectedUser.username)
    $passwordFld.val(selectedUser.password)
    $firstNameFld.val(selectedUser.firstName)
    $lastNameFld.val(selectedUser.lastName)
    $roleFld.val(selectedUser.role)
  }

  function updateUser() {
    selectedUser.username =  $usernameFld.val()
    selectedUser.password =  $passwordFld.val()
    selectedUser.firstName = $firstNameFld.val()
    selectedUser.lastName = $lastNameFld.val()
    selectedUser.role =  $roleFld.val()

    // Clear the input field
    $usernameFld.val('')
    $passwordFld.val('')
    $firstNameFld.val('')
    $lastNameFld.val('')

    userService.updateUser(selectedUser._id, selectedUser)
      .then(function (status) {
        var index = users.findIndex(user => user._id === selectedUser._id)
        users[index] = selectedUser
        renderUsers(users)
      })
  }

  function renderUsers(users) {
    $tbody.empty()
    for (var i=0; i < users.length; i++) {
      var user = users[i]
      $tbody.append(`
        <tr class="wbdv-template wbdv-user wbdv-hidden">
          <td class="wbdv-username">${user.username}</td>
          <td>&nbsp;</td>
          <td class="wbdv-first-name">${user.firstName}</td>
          <td class="wbdv-last-name">${user.lastName}</td>
          <td class="wbdv-role">${user.role}</td>
          <td class="wbdv-actions float-end" style="white-space: nowrap">
            <button class="wbdv-remove-btn">
              <i id="${i}" class="wbdv-remove fa fa fa-times wbdv-icon"></i>
            </button>
            <button class="wbdv-edit-btn">
              <i id="${i}" class="wbdv-edit fa fa fa-pencil wbdv-icon"></i>
            </button>
        </tr>
      `
      )
    }
    $(".wbdv-remove").click(deleteUser)
    $(".wbdv-edit").click(selectUser)
  }

  function findAllUsers() {
    userService.findAllUsers().then(function (allUsers) {
      users = allUsers
      renderUsers(users)
    })
  }

  function searchUsers() {
    var username =  $usernameFld.val()
    var firstName = $firstNameFld.val()
    var lastName = $lastNameFld.val()
    var role =  $roleFld.val()

    var foundUsers = users.filter(user => user.username === username
        || user.firstName === firstName
        || user.lastName === lastName
        || user.role === role
    )

    renderUsers(foundUsers)
  }


}) ();